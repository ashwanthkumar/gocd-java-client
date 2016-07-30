[![Build Status](https://snap-ci.com/ashwanthkumar/gocd-java-client/branch/master/build_image)](https://snap-ci.com/ashwanthkumar/gocd-java-client/branch/master)
# gocd-java-client

Attempt at building a simple Java client for accessing information in GoCD. Specifically built for using in GoCD plugins, who needs to access certain information from the server.

## Usage
```java
GoCD client = new GoCD("http://localhost", "admin", "badger");
Map<Integer, PipelineRunStatus> statusMap = client.pipelineRunStatus("Build-Linux");
```

## Credits
Most of this was extracted from work done at [gocd-janitor](https://github.com/ashwanthkumar/gocd-janitor) and [gocd-slack-build-notifier](https://github.com/ashwanthkumar/gocd-slack-build-notifier). Credits goes to all those you've contributed to the respective projects.

## License
Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
