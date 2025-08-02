@RestController
public class HealthController {

    @GetMapping("/healthz")
    public ResponseEntity<String> healthCheck() {
        try {
            // Simulate delay for 3 minutes (for readiness probe failure testing)
            Thread.sleep(180000);
        } catch (InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Interrupted");
        }
        return ResponseEntity.ok("Application is healthy");
    }
}
