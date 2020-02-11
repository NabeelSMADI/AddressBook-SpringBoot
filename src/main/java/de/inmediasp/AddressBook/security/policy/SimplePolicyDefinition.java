package de.inmediasp.AddressBook.security.policy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

@Component
public class SimplePolicyDefinition implements PolicyDefinition {
	private List<PolicyRule> rules;
	
	@PostConstruct
	private void init(){
		ExpressionParser exp = new SpelExpressionParser();
		rules = new ArrayList<>();
		
		PolicyRule newRule = new PolicyRule();
		newRule.setName("Admin");
		newRule.setDescription("Admin can do all.");
		newRule.setTarget(exp.parseExpression("subject.getUsername() == 'admin'"));
		newRule.setCondition(exp.parseExpression("true"));
		rules.add(newRule);
		
	    newRule = new PolicyRule();
		newRule.setName("ResourceOwner");
		newRule.setDescription("Resource owner should have access to it.");
		newRule.setTarget(exp.parseExpression("subject.getUsername() == 'admin2' && action == 'Read'"));
		newRule.setCondition(exp.parseExpression("subject.getUsername() == resource.body.getOwner()"));
		rules.add(newRule);
		
	    newRule = new PolicyRule();
		newRule.setName("deleteAll");
		newRule.setDescription("deleteAll");
		newRule.setTarget(exp.parseExpression("subject.getUsername() != 'admin' && action == 'deleteAll'"));
		newRule.setCondition(exp.parseExpression("false"));
		rules.add(newRule);
	}
	public List<PolicyRule> getAllPolicyRules() {
		return rules;
	}

}
