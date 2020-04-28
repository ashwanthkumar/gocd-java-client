[![Build Status](https://travis-ci.org/ashwanthkumar/gocd-java-client.svg?branch=master)](https://travis-ci.org/ashwanthkumar/gocd-java-client)
# gocd-java-client

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/in.ashwanthkumar/gocd-java-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/in.ashwanthkumar/gocd-java-client)

Attempt at building a Java client for programmatically accessing GoCD.
Specifically built for using in GoCD plugins, which needs to access certain information from the server.

## Usage
```java
GoCD client = new GoCD("http://localhost", "admin", "badger");
// from v19.2.0 GoCD Server onwards
GoCD client = new GoCD("http://localhost", new BearerTokenAuthentication("PERSONAL_ACCESS_TOKEN"));
Map<Integer, PipelineRunStatus> statusMap = client.pipelineRunStatus("Build-Linux");
```

**Note**: You need at least JDK 9 to use this library, but it is built and tested against JDK 11.

## Credits
Up to `0.0.7`, most of this is based on the work done at [gocd-janitor](https://github.com/ashwanthkumar/gocd-janitor)
and [gocd-slack-build-notifier](https://github.com/ashwanthkumar/gocd-slack-build-notifier).
Credit goes to all those who have contributed to the respective projects.

## License
Licensed under the Apache License, Version 2.0: http://www.apache.org/licenses/LICENSE-2.0
