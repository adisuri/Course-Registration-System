/**
 * 
 */
package com.flipkart.constants;

/**
 * @author JEDI-7
 *
 */
public enum ModeOfPayment {
	
	CARD,NET_BANKING,CASH,CHEQUE,SCHOLARSHIP;
	
	/**
	 * Method to get Mode of Payment
	 * @param value
	 * @return Mode of Payment
	 */
	public static ModeOfPayment getModeofPayment(int value)
	{
		switch(value)
		{
			case 1:
				return ModeOfPayment.CARD;
			case 2:
				return ModeOfPayment.NET_BANKING;
			case 3:
				return ModeOfPayment.CASH;
			case 4:
				return ModeOfPayment.CHEQUE;
			case 5:
				return ModeOfPayment.SCHOLARSHIP;
			default:
				return null;
				
		}
			
	}
}
