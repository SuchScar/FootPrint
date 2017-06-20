package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SetTripParticipantListEntity implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -7920169250307025813L;
private List<TripParticipantEntity> TripParticipant = new ArrayList<TripParticipantEntity>();
	
   public SetTripParticipantListEntity(){}
   
	public SetTripParticipantListEntity(List<TripParticipantEntity> TripParticipant){
		this.TripParticipant = TripParticipant;
	}
	
	public List<TripParticipantEntity> getTripParticipant() {
		return TripParticipant;
	}

	public void setTripParticipant(List<TripParticipantEntity> tripParticipant) {
		TripParticipant = tripParticipant;
	}

	public int getSize(){
		return TripParticipant.size();
	}
	
	public TripParticipantEntity getItem(int arg0){
		return TripParticipant.get(arg0);
	}
}
