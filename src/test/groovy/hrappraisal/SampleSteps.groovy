package hrappraisal

import io.cucumber.groovy.EN
import io.cucumber.groovy.Hooks

// Add functions to register hooks and steps to this script.
this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

var result;

// Define a world that represents the test environment.
// Hooks can set up and tear down the environment and steps
// can change its state, e.g. store values used by later steps.
class CustomWorld {
    def result

    String customMethod() {
        "foo"
    }
}

// Create a fresh new world object as the test environment for each scenario.
// Hooks and steps will belong to this object so can access its properties
// and methods directly.
World {
    new CustomWorld()
}

// This closure gets run before each scenario
// and has direct access to the new world object
// but can also make use of script variables.
Before() {
    assert "foo" == customMethod()

}

// Register another that also gets run before each scenario tagged with @notused.
Before("@notused") {
    throw new RuntimeException("Never happens")
}

// Register another that also gets run before each scenario tagged with
// (@notused or @important) and @alsonotused.
Before("(@notused or @important) and @alsonotused") {
    throw new RuntimeException("Never happens")
}

Given(~/^I have a calculator$/) {
    println "Asserted given";
}

When(~/^I add (\d+) and (\d+)$/) { int a, int b ->
    result = a + b
}

Then(~/the result should be (\d+)$/) { double expected ->
    assert expected == result
}