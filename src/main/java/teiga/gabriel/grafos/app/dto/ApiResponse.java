package teiga.gabriel.grafos.app.dto;

import java.math.BigInteger;

public record ApiResponse(
        BigInteger result,
        long timeToExecute
    ) {}
