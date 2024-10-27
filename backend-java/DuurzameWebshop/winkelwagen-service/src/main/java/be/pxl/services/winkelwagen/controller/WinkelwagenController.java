package be.pxl.services.winkelwagen.controller;

import be.pxl.services.winkelwagen.service.IWinkelwagenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/winkelwagen")
@RequiredArgsConstructor
public class WinkelwagenController {
    IWinkelwagenService winkelwagenService;
}
