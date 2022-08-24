# RandomTestPrioritiserPlugin

This plugin uses the TestPrioritiserFactory extension point to run half of the test suite against each mutation.

It's similar to the bogus sort algorithm. By luck, we might hit a faster time than regular pitest. This is only the case if we actually manage to test only the neccessary mutations/use the neccessary test suites.

This plugin will make mutation testing fast in b/c. In w/c it will make it slow. (see Bogus Sort)

# pitest-plugins

Example plugins for pitest.

This repository contains examples of creating plugins for the pitest mutation testing system. Several of them may be useful in their own right, particularly to those working in academic research.

These plugins are not currently released. To install locally from source

```bash
mvn install
```

To use these plugins via maven add them as dependencies to the pitest-maven plugin (i.e **not** to your project dependencies) e.g

```xml
      <plugin>
        <groupId>org.pitest</groupId>
        <artifactId>pitest-maven</artifactId>
        <version>0.34-SNAPSHOT</version>

        <dependencies>
          <dependency>
            <groupId>org.pitest.plugins</groupId>
            <artifactId>pitest-high-isolation-plugin</artifactId>
            <version>0.1-SNAPSHOT</version>
          </dependency>
        </dependencies>

        <configuration>
		blah
        </configuration>
      </plugin>
```
