package com.company.oks.security;

import com.company.oks.entity.Order;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "OrderReader", code = "order-reader")
public interface OrderReaderRole {
    @MenuPolicy(menuIds = "Order_.browse")
    @ScreenPolicy(screenIds = {"Order_.browse", "Order_.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = Order.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Order.class, actions = EntityPolicyAction.READ)
    void order();
}