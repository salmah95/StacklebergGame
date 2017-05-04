
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

//This class returns the prices of the leader and follower for the first 100 days
final class getInfo
 extends PlayerImpl
 {
	//An array to hold the records of both the follower and leader 
	private final static Record [] [] records = new Record [100][2];
	//an array to hold just the prices of the follower and leader 
	private final static double[][] info = new double [100][2];

	//constructor method
	private getInfo()
			throws RemoteException, NotBoundException 
	{
		super(PlayerType.LEADER, "Our Leader");
		
	}
	
	//this returns the record for a specified day, this method stores all the records in the array 
	public Record query(final PlayerType p_type,
			final int p_queryDate)
			throws RemoteException 
	{
		for(int x= 0; x <= 100; x++)
	    {
		  
		  records [x][1] = query(PlayerType.LEADER, x);
		  records [x][2] = query(PlayerType.FOLLOWER, x);
		  
		}
		
		return null;
	}

    //i dont use this but it needs it because its an inherited class 
	@Override
	public void proceedNewDay(int p_date) 
			throws RemoteException 
	{
		// TODO Auto-generated method stub
		
	}

	//from the record array specifically get the prices so you have an array of ints instead of records
	public static double[][] getRecords() throws RemoteException 
	{
		for(int x= 0; x <= 100; x++)
	    {
		  
		  info[x][1] = (int) records[x][1].m_leaderPrice; 
		  info[x][2] = (int) records[x][2].m_followerPrice; 
		  
		}
		
		return info;
	}
	
 }