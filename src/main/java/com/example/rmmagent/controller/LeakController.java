// Inside your controller
private List<String> memoryLeakList = new ArrayList<>();

@GetMapping("/leak")
public String createLeak() {
    for (int i = 0; i < 1000000; i++) {
        memoryLeakList.add(UUID.randomUUID().toString());
    }
    return "Memory leaking...";
}
