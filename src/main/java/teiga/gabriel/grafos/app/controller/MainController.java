package teiga.gabriel.grafos.app.controller;

import com.sun.tools.javac.Main;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teiga.gabriel.grafos.app.dto.ApiRequest;
import teiga.gabriel.grafos.app.dto.ApiResponse;
import teiga.gabriel.grafos.domain.services.GrafoService;

import java.io.IOException;

@RestController
@RequestMapping("/api/graph")
public class MainController {
    public final GrafoService grafoService;

    public MainController(GrafoService grafoService) {
        this.grafoService = grafoService;
    }

    @PostMapping
    ResponseEntity<ApiResponse> getCaseResult(@RequestBody ApiRequest request) throws IOException {
        ApiResponse apiResponse = grafoService.executarCaso(request.caso());

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }
}
