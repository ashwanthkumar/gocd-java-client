# gocd-java-client

Attempt at building a simple Java client for accessing information in GoCD. Specifically built for using in GoCD plugins, who needs to access certain information from the server.

## Usage
```java
GoCD client = new GoCD("http://localhost", "admin", "badger");
Map<Integer, PipelineRunStatus> statusMap = client.pipelineRunStatus("Build-Linux");
```

## License
Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
