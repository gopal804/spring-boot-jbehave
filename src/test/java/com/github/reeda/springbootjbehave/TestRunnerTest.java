package com.github.reeda.springbootjbehave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.gherkin.GherkinStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.PrintStreamStepdocReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.ParameterConverter;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by andrew on 11/18/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { ApplicationToTest.class})
public class TestRunnerTest extends JUnitStories {

    @Autowired
    private ApplicationContext applicationContext;

    public TestRunnerTest() {
        initJBehaveConfiguration();
    }

    private void initJBehaveConfiguration() {
        Class<?> thisClass = this.getClass();
        useConfiguration(new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(thisClass.getClassLoader()))
                .usePendingStepStrategy(new FailingUponPendingStep())
                .useStepdocReporter(new PrintStreamStepdocReporter())
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(thisClass))
                        .withDefaultFormats()
                        .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML, Format.STATS)
                        .withCrossReference(new CrossReference())
                        .withFailureTrace(true))
                .useParameterConverters(new ParameterConverters(new TableTransformers())
                        .addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd"))))
                .useStoryParser(new GherkinStoryParser())
                .useStepMonitor(new SilentStepMonitor()));
    }

    @Override

    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), applicationContext);
    }

    protected List<String> storyPaths() {
		String userDir = System.getProperty("user.dir");
		List<String> findPaths = new StoryFinder().findPaths(userDir + "/src/test/resources", "**/*.story", "**/excluded*.story");
		return findPaths;
        
    }

}
