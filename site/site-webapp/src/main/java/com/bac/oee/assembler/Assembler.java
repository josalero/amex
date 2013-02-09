package com.bac.oee.assembler;

// TODO: Auto-generated Javadoc
/**
 * The Interface Assembler.
 * 
 * @param <T>
 *            the generic type
 * @param <To>
 *            the generic type
 */
public interface Assembler<T, To> {

	/**
	 * Assemble object {@link To} from object {@link T}.
	 * 
	 * @param objectToAssemble
	 *            the object to assemble
	 * 
	 * @return the to
	 */
	public To assemble(T objectToAssemble);

	/**
	 * Dis assemble.
	 * 
	 * @param objectToAssemble
	 *            the object to assemble
	 * 
	 * @return the t
	 */
	public T disAssemble(To objectToAssemble);
}