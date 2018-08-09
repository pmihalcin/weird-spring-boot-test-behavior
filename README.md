This sample app reproduces issue described here: https://stackoverflow.com/questions/51733707/weird-behavior-of-springboottest-based-spock-specifications/51740233#51740233

When `SpecificMerchantCriterionPagedResourcesProcessorSpec` is executed alone, it is successful.

When `JmsSimulatedBusinessFailureSpec` is executed alone, it is successful as well.

When `SpecificMerchantCriterionPagedResourcesProcessorSpec` and `JmsSimulatedBusinessFailureSpec` are executed in 1 batch in the mentioned order, `JmsSimulatedBusinessFailureSpec` fails as if `FailureSimulator` was not imported at all.

The thing is I can see that `FailureSimulator` is in `ApplicationContext` when I examine all beans and also aspect was recognized by Spring (can be seen in logs).

When I add `@DirtiestContext` annotation on `SpecificMerchantCriterionPagedResourcesProcessorSpec` and reexecute previous batch, it works.
So by putting annotation on a test which doesn't fail, I fix the other test.
