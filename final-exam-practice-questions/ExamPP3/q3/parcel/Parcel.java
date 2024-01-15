package q3.parcel;

public class Parcel implements Comparable<Parcel> {
	private final int id;
	private final int weight;
	private final int allowedDays;
	private final Sender sender;
	private final Recipient recipient;

	public Parcel(int id,int weight, int allowedDays, Sender sender, Recipient recipient) {
		this.id = id;
		this.weight = weight;
		this.allowedDays = allowedDays;
		this.sender = sender;
		this.recipient = recipient;
	}

	public Parcel(Parcel parcel) {
		this.id = parcel.id;
		this.weight = parcel.weight;
		this.allowedDays = parcel.allowedDays;
		this.sender = new Sender(parcel.sender);
		this.recipient = new Recipient(parcel.recipient);
	}

	public int getId(){
		return id;
	}

	public int getWeight() {
		return weight;
	}

	public int getAllowedDays() {
		return allowedDays;
	}

	public Sender getSender() {
		return sender;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	//         parcel1 = new Parcel(1,23,2,sender1,recipient1);		recipient1 = new Recipient(new CartesianCoordinate(30,40));
	//        parcel2 = new Parcel(2,21,32,sender2,recipient2);
	//        parcel3 = new Parcel(3,23,34,sender3,recipient3);
	//        parcel4 = new Parcel(4,23,2,sender4,recipient4);
	//        parcel5 = new Parcel(5,23,5,getSender(10,30), getRecipient(-28,23));

	public static void main(String[] args) {
		var parcel1 = new Parcel(1,23,2, new Sender(new CartesianCoordinate(-30,-50)), new Recipient(new CartesianCoordinate(30,40)));
		var parcel5 = new Parcel(5,23,5,new Sender(new CartesianCoordinate(10,30)), new Recipient(new CartesianCoordinate(-28, 23))); // getSender(10,30)
		System.out.println("parcel1.compared2(parcel5): " + parcel1.compareTo(parcel5));
	}

	@Override
	public int compareTo(Parcel other) {
		// TODO
		// START YOUR CODE

		// a)
		// Impose a 'natural order' on Parcel using the CompareTo method in the Parcel class. The ordering of parcels to be implemented is defined by:
		//The distance from the Warehouse to the Recipient (highlighted in green in the above image) is in ascending ord

		var wareHouseLoc = new CartesianCoordinate(20, 10);
		// avoid rounding problem when (int)
		var thisDist = this.recipient.getLocation().getDistance(wareHouseLoc);
		var otherDist = other.recipient.getLocation().getDistance(wareHouseLoc);
		System.out.println("   thisDist:" + thisDist);
		System.out.println("   otherDist:" + otherDist);

		if (thisDist < otherDist) {
			return -1;
		} else if (thisDist> otherDist) {
			return 1;
		} else {
			return 0;
		}

		// return (int) (this.recipient.getLocation().getDistance(wareHouseLoc) - other.recipient.getLocation().getDistance(wareHouseLoc));

		// return 0;
		// END YOUR CODE
	}

	@Override
	public String toString() {
		return "Parcel{" +
				"id=" + id +
				", sender=" + sender +
				", recipient=" + recipient +
				'}';
	}

}
