package com.agreent.openbuild.core.config.axon;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingMessageHandlerInterceptor
  implements MessageHandlerInterceptor<CommandMessage<?>> {

  @Override
  public Object handle(
    UnitOfWork<? extends CommandMessage<?>> unitOfWork, InterceptorChain interceptorChain)
    throws Exception {
    log.info("Handling command: {}", unitOfWork.getMessage().getPayloadType().getSimpleName());
    return interceptorChain.proceed();
  }
}
