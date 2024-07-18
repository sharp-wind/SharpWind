package com.example.guestbook.Controller;

import com.example.guestbook.Dto.GuestbookDTO;
import com.example.guestbook.Dto.PageRequestDTO;
import com.example.guestbook.Service.GuestbookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {
    private final GuestbookService guestbookService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO,Model model) {
        log.info("GET request : /list");
        model.addAttribute("pageResponseDTO", guestbookService.list(pageRequestDTO));

    }
    @GetMapping("/register")
    public void register() {

        log.info("GET request : /guestbook/register");

    }

    @PostMapping("/register")
    public String register(GuestbookDTO guestbookDTO, RedirectAttributes redirectAttributes) {

        log.info("POST request : /guestbook/register");

        Long registeredGno = guestbookService.register(guestbookDTO);

        log.info("registered gno : " + registeredGno);

        redirectAttributes.addFlashAttribute("msg", "success");

        return "redirect:/list";
    }

    @GetMapping({"/read", "/update"})
    public void read(HttpServletRequest request, Long gno, @ModelAttribute("pageRequestDTO") PageRequestDTO dto, Model model) {

        String url = request.getRequestURI().contains("read") ? "read" : "update";

        log.info("GET request : /guestbook/" + url);

        model.addAttribute("guestbookDTO", guestbookService.read(gno));

    }

    @PostMapping("/update")
    public String update(GuestbookDTO guestbookDTO, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {

        log.info("POST request : /guestbook/update");

        guestbookService.update(guestbookDTO);

        redirectAttributes.addAttribute("gno", guestbookDTO.getGno());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());

        return "redirect:/read";
    }
    @PostMapping("/remove")
    public String remove(Long gno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {

        log.info("POST request : /guestbook/remove");

        guestbookService.remove(gno);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());

        redirectAttributes.addFlashAttribute("msg", "removed " + gno);

        return "redirect:/list";
    }



}
