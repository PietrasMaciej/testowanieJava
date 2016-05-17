package lab04;



public interface IMyList {
	public void add(Bike b);
	public IMyList getAllBikes();
	public Bike get(int index);
	public int size();
}
