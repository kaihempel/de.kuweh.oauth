package de.kuweh.oauth.dao.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Object transformator
 * 
 * @author Kai Hempel
 *
 * @param <I>
 * @param <O>
 */
public class Transformator<I, O> {

	/**
	 * Input instance
	 */
	private I input = null;
	
	/**
	 * Output instance
	 */
	private O output = null;
	
	/**
	 * Default constructor
	 */
	public Transformator() {}
	
	/**
	 * Constructor with input instance
	 * 
	 * @param input
	 */
	public Transformator(I input, O output)
	{
		setInputInstance(input);
		setOutputInstance(output);
	}
	
	/**
	 * Sets the input instance.
	 * Executes fill method, if a output instance already exists.
	 * 
	 * @param input
	 * @return Current transformator instance for chaining
	 */
	public Transformator<I, O> setInputInstance(I input)
	{
		this.input = input;
		return this;
	}
	
	/**
	 * Returns the current input instance
	 * 
	 * @return I
	 */
	public I getInputInstance()
	{
		return input;
	}
	
	/**
	 * 
	 * @param output
	 * @return Current transformator instance for chaining
	 */
	public Transformator<I, O> setOutputInstance(O output)
	{
		this.output = output;
		return this;
	}
	
	/**
	 * Returns the output instance
	 * 
	 * @return O
	 */
	public O getOutputInstance()
	{	
		return output;
	}
	
	/**
	 * Fills the output instance with all possible values of the input instance
	 */
	public void fillOutputInstance()
	{
		Field[] inputFields = input.getClass().getDeclaredFields();
		ArrayList<String> outputfieldNames = getInstanceFieldNames(output);
		
		for (Field field : inputFields) {
			if (outputfieldNames.contains(field.getName())) {
				setOutputValue(field);
			}
		}
	}
	
	/**
	 * Sets the value into the output instance
	 * 
	 * @param field
	 */
	private void setOutputValue(Field field)
	{
		Method inputGetter = getInputGetterMethod(field);
		Method outputSetter = getOutputSetterMethod(field);
		
		// Missing method: abort further execution
		if (isTransferable(inputGetter, outputSetter) == false) {
			return;
		}
		
		// Transfer the value by method execution
		try {
		    outputSetter.invoke(output, inputGetter.invoke(input));
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * Returns the setter method instance if the method exists on the output instance
	 * 
	 * @param field
	 * @return
	 */
	private Method getOutputSetterMethod(Field field)
	{
		// Source field type
		Class<?>[] parameterTypes = new Class[1];
		parameterTypes[0] = field.getType();
		
		// Field name depending setter method
		String methodName = "set" + ucFrist(field.getName());
		
		// Check if the setter exists
		try {
			return output.getClass().getMethod(methodName, parameterTypes);
		} 
		// If not abort further execution
		catch (NoSuchMethodException e) {
			return null;
		}
	}
	
	/**
	 * Returns the getter method instance if the method exists on the input instance
	 * 
	 * @param field
	 * @return
	 */
	private Method getInputGetterMethod(Field field)
	{
		// Field name depending setter method
		String methodName = "get" + ucFrist(field.getName());
		
		// Check if the setter exists
		try {
			return input.getClass().getMethod(methodName);
		} 
		// If not abort further execution
		catch (NoSuchMethodException e) {
			return null;
		}
	}
	
	/**
	 * Checks if both methods exists
	 * 
	 * @param getter
	 * @param setter
	 * @return True if both methods exists
	 */
	private boolean isTransferable(Method getter, Method setter)
	{
		return (getter != null && setter != null);
	}
	
	/**
	 * Transforms the first letter into uppercase
	 * 
	 * @param name
	 * @return The resulting string
	 */
	private String ucFrist(String name)
	{
		if (name == null || name.length() == 0) {
			return null;
		}
		
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	/**
	 * Returns a list of field names of the given instance
	 * 
	 * @param instance
	 * @return All fields of the corresponding class
	 */
	private ArrayList<String> getInstanceFieldNames(Object instance) 
	{
		ArrayList<String> fieldNames = new ArrayList<>();
		
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field field : fields) {
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}
	
}
