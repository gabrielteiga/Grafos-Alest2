package teiga.gabriel.grafos.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teiga.gabriel.grafos.app.dto.ApiRequest;
import teiga.gabriel.grafos.app.dto.ApiResponse;

@RestController
@RequestMapping("/api/graph")
public class MainController {
    @PostMapping
    ResponseEntity<ApiResponse> getCaseResult(ApiRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new ApiResponse(request.caso(), "Tstes")
                );
    }
}
