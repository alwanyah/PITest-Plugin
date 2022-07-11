package org.pitest.randomtests;

import org.pitest.classpath.CodeSource;
import org.pitest.coverage.CoverageDatabase;

import java.util.Properties;

/**
 * Runs the entire test suite against each mutation.
 */
public class RandomTestPrioritiserFactory implements TestPrioritiserFactory {

  public String description() {
    return "Random tests prioritiser";
  }

  public TestPrioritiser makeTestPrioritiser(Properties properties, CodeSource code,
                                             CoverageDatabase coverage) {
    return new RandomTestPrioritiser(coverage);
  }

}