package com.nuptsast.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
public class FileUploadController {

  public static final String ROOT = "upload-dir";
  private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);
  private final ResourceLoader resourceLoader;

  @Autowired
  public FileUploadController(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/files")
  @PreAuthorize("hasRole('ROLE_ADMIN') or " +
    "(hasRole('ROLE_USER') and new java.util.GregorianCalendar().after(new java.util.GregorianCalendar(2016,08,25,12," +
    "00)))")
  public String provideUploadInfo(Model model) throws IOException {

    model.addAttribute("files", Files.walk(Paths.get(ROOT))
      .filter(path -> !path.equals(Paths.get(ROOT)))
      .map(path -> Paths.get(ROOT).relativize(path))
      .map(path -> linkTo(methodOn(FileUploadController.class).getFile(path.toString())).withRel(path.toString()))
      .collect(Collectors.toList()));

    return "uploadForm";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/files/{filename:.+}")
  @ResponseBody
  @PreAuthorize("hasRole('ROLE_ADMIN') or " +
    "(hasRole('ROLE_USER') and new java.util.GregorianCalendar().after(new java.util.GregorianCalendar(2016,08,25,12," +
    "00)))")
  public ResponseEntity<?> getFile(@PathVariable String filename) {
    try {
      return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @RequestMapping(method = RequestMethod.POST, value = "/files")
  public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {

    if (!file.isEmpty()) {
      try {
        Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
        redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded " + file.getOriginalFilename() + "!");
      } catch (IOException | RuntimeException e) {
        redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
      }
    } else {
      redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
    }

    return "redirect:/files";
  }

}