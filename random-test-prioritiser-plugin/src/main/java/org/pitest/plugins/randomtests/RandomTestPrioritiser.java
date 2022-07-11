package org.pitest.randomtests;

import org.pitest.classinfo.ClassName;
import org.pitest.coverage.BlockLocation;
import org.pitest.coverage.CoverageDatabase;
import org.pitest.coverage.TestInfo;
import org.pitest.mutationtest.engine.MutationDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * For each mutation this Prioritiser randomly picks 50% of the tests and sorts them by execution speed
 *
 */
public class RandomTestPrioritiser implements TestPrioritiser {

  private final CoverageDatabase coverage;

  private static final int TIME_WEIGHTING_FOR_DIRECT_UNIT_TESTS = 1000;


  public RandomTestPrioritiser(CoverageDatabase coverage) {
    this.coverage = coverage;
  }

  @Override
  public List<TestInfo> assignTests(MutationDetails mutation) {
    return prioritizeTests(mutation.getClassName(), pickTestsRandomly(mutation));
  }

  private List<TestInfo> prioritizeTests(ClassName clazz,
                                         List<TestInfo> testsForMutant) {
    testsForMutant.sort(new TestInfoPriorisationComparator(clazz, TIME_WEIGHTING_FOR_DIRECT_UNIT_TESTS));
    return testsForMutant;
  }

  private List<TestInfo> pickTestsRandomly(MutationDetails mutation) {
    List<TestInfo> tests = new ArrayList<>(pickTests(mutation));

    Collections.shuffle(tests);

    // Only half the tests
    tests = tests.subList(0, tests.size() / 2);

    return tests;
  }

  private Collection<TestInfo> pickTests(MutationDetails mutation) {
    return mutation.getBlocks().stream()
            .map(block -> new BlockLocation(mutation.getId().getLocation(), block))
            .flatMap(loc -> this.coverage.getTestsForBlockLocation(loc).stream())
            .collect(Collectors.toSet());
  }
}