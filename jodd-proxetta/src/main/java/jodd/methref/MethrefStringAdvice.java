// Copyright (c) 2003-2013, Jodd Team (jodd.org). All Rights Reserved.

package jodd.methref;

import jodd.proxetta.ProxyAdvice;
import jodd.proxetta.ProxyTarget;

/**
 * Advice applied only on methods that return Strings.
 * Simply returns target name as a string.
 */
public class MethrefStringAdvice implements ProxyAdvice {

	public Object execute() {
		return ProxyTarget.targetMethodName();
	}
}
