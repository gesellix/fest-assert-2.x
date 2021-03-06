/*
 * Created on Jun 3, 2012
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2010-2012 the original author or authors.
 */
package org.fest.assertions.internal.maps;

import static org.fest.assertions.data.MapEntry.entry;
import static org.fest.assertions.error.ShouldNotContainValue.shouldNotContainValue;
import static org.fest.assertions.test.MapFactory.map;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.fest.util.FailureMessages.actualIsNull;

import static org.mockito.Mockito.verify;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.internal.Maps;
import org.fest.assertions.internal.MapsBaseTest;

/**
 * Tests for <code>{@link Maps#assertDoesNotContainValue(AssertionInfo, Map, Object)}</code>.
 * 
 * @author Nicolas François
 * @author Joel Costigliola
 */
public class Maps_assertDoesNotContainValue_Test extends MapsBaseTest {

  @SuppressWarnings("unchecked")
  @Override
  @Before
  public void setUp() {
    super.setUp();
    actual = (Map<String, String>) map(entry("name", "Yoda"), entry("color", "green"));
  }

  @Test
  public void should_pass_if_actual_contains_given_value() {
    maps.assertDoesNotContainValue(someInfo(), actual, "veryOld");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    maps.assertDoesNotContainValue(someInfo(), null, "veryOld");
  }

  @Test
  public void should_success_if_value_is_null() {
    maps.assertDoesNotContainValue(someInfo(), actual, null);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_value() {
    AssertionInfo info = someInfo();
    String value = "Yoda";
    try {
      maps.assertDoesNotContainValue(info, actual, value);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldNotContainValue(actual, value));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
