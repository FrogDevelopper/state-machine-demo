package fr.demo.state.order.config;

import fr.demo.state.common.MessageService;
import fr.demo.state.common.What;
import fr.demo.state.order.OrderEvent;
import fr.demo.state.order.OrderState;
import fr.demo.state.order.guard.PaymentGuard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:order-machine-context-test.xml")
public class OrderMachineConfigTest {

    @Autowired
    private StateMachineFactory<OrderState, OrderEvent> orderMachineFactory;

    @SpyBean
    private MessageService messageService;

    @MockBean
    private PaymentGuard paymentGuard;

    @Test
    public void test_guard_false() throws Exception {
        // given
        Mockito.doReturn(false).when(paymentGuard).evaluate(Mockito.any());

        StateMachineTestPlan<OrderState, OrderEvent> plan =
                StateMachineTestPlanBuilder.<OrderState, OrderEvent>builder()
                        .stateMachine(orderMachineFactory.getStateMachine(What.ORDER.name()))
                        .step()
                        .expectState(OrderState.INITIAL)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.CREATE)
                        .expectState(OrderState.DRAFT)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.VALIDATE)
                        .expectState(OrderState.WAITING_PAYMENT)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.PAY)
                        .expectState(OrderState.WAITING_PAYMENT)

                        .and()
                        .build();
        plan.test();

        Mockito.verifyZeroInteractions(messageService);
    }

    @Test
    public void test_guard_true() throws Exception {
        // given
        Mockito.doReturn(true).when(paymentGuard).evaluate(Mockito.any());

        StateMachineTestPlan<OrderState, OrderEvent> plan =
                StateMachineTestPlanBuilder.<OrderState, OrderEvent>builder()
                        .stateMachine(orderMachineFactory.getStateMachine(What.ORDER.name()))
                        .step()
                        .expectState(OrderState.INITIAL)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.CREATE)
                        .expectState(OrderState.DRAFT)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.VALIDATE)
                        .expectState(OrderState.WAITING_PAYMENT)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.PAY)
                        .expectState(OrderState.PREPARING)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.SEND)
                        .expectState(OrderState.DELIVERING)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.RECEIPT)
                        .expectState(OrderState.DONE)

                        .and()
                        .build();
        plan.test();

        Mockito.verify(messageService, Mockito.times(2)).sendMail(Mockito.anyString());
    }

    @Test
    public void cancel() throws Exception {
        StateMachineTestPlan<OrderState, OrderEvent> plan =
                StateMachineTestPlanBuilder.<OrderState, OrderEvent>builder()
                        .stateMachine(orderMachineFactory.getStateMachine(What.ORDER.name()))
                        .step()
                        .expectState(OrderState.INITIAL)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.CREATE)
                        .expectState(OrderState.DRAFT)

                        .and()
                        .step()
                        .sendEvent(OrderEvent.CANCEL)
                        .expectState(OrderState.CANCELED)

                        .and()
                        .build();
        plan.test();

        Mockito.verifyZeroInteractions(messageService);
    }

}