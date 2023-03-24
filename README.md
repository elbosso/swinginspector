# Swing-Inspector

<!---
[![start with why](https://img.shields.io/badge/start%20with-why%3F-brightgreen.svg?style=flat)](http://www.ted.com/talks/simon_sinek_how_great_leaders_inspire_action)
--->
[![GitHub release](https://img.shields.io/github/release/elbosso/swinginspector/all.svg?maxAge=1)](https://GitHub.com/elbosso/swinginspector/releases/)
[![GitHub tag](https://img.shields.io/github/tag/elbosso/swinginspector.svg)](https://GitHub.com/elbosso/swinginspector/tags/)
[![GitHub license](https://img.shields.io/github/license/elbosso/swinginspector.svg)](https://github.com/elbosso/swinginspector/blob/master/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/elbosso/swinginspector.svg)](https://GitHub.com/elbosso/swinginspector/issues/)
[![GitHub issues-closed](https://img.shields.io/github/issues-closed/elbosso/swinginspector.svg)](https://GitHub.com/elbosso/swinginspector/issues?q=is%3Aissue+is%3Aclosed)
[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/elbosso/swinginspector/issues)
[![GitHub contributors](https://img.shields.io/github/contributors/elbosso/swinginspector.svg)](https://GitHub.com/elbosso/swinginspector/graphs/contributors/)
[![Github All Releases](https://img.shields.io/github/downloads/elbosso/swinginspector/total.svg)](https://github.com/elbosso/swinginspector)
[![Website elbosso.github.io](https://img.shields.io/website-up-down-green-red/https/elbosso.github.io.svg)](https://elbosso.github.io/)

[![Demo-Video](http://img.youtube.com/vi/OhmDvpQBJ0g/0.jpg)](http://www.youtube.com/watch?v=OhmDvpQBJ0g "Demo")

## Preconditions

Java 11

## Build
mvn -U package assembly:single

## Usage

### Starting a client application

Any Java application using Swing for its GUI can be used with the tools provided here - just start it with the
jar file built in target as premain-jar like this:

```
java -javaagent:<path-to>/swinginspector-x.y.z-SNAPSHOT-jar-with-dependencies.jar -jar <clientapp>.jar
```

After starting the application in question, one more frame opens -  a rather small one, so watch out - it
is even possible that this frame opens behind others. Its title is Manager and it initially contains only one button
named register. Push that button and the inspection begins: on mouse over, the deepest component is highlighted with 
a dashed red border. Whenever the user clicks in this mode, the inspection mode ends - at that point it is important to
increase the size of the Manager frame. It now not only holds the register button but also a generated GUI for
adjusting properties of the component the user clicked on while in inspection mode. Changes made here are instantly
applied to the component in question.

