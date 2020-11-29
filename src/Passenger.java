/**
 * represents passenger's
 * gets and provides passenger's information
 * @author lemon
 *
 */
public class Passenger {
	
		private String name;
		private String seat;
		private String fClass;
		private String groupName;
		private String groupType;
		
		/**
		 * construct passenger object
		 */
		public Passenger() {}
		/**
		 * Construct passenger object
		 * @param name gets passenter's name
		 * @param seat gets passenter's seat number
		 * @param fClass gets passenter's service type
		 * @param groupName get group name (if go with group)
		 * @param groupType define individual passenger or group
		 */
		public Passenger (String name, String seat, String fClass, String groupName, String groupType ) {
			this.name = name;
			this.seat = seat;
			this.fClass = fClass;
			this.groupName = groupName;
			this.groupType = groupType;
		}
		
		/**
		 * get passenger's name
		 * @return passenger's name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 *to set passenger's name 
		 * @param name gets passenger's name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		
		/**
		 * to get service type
		 * @return passenger's serivce type
		 */
		public String getFClass() {
			return this.fClass ;
		}
		
		/**
		 * to set service type for passenger
		 * @param fClass take service type 
		 */
		public void setFClass(String fClass) {
			this.fClass = fClass;
		}	

		/**
		 * to get passenger's seat number
		 * @return passenger's seat number
		 */
		public String getSeat() {
			return seat;
		}

		/**
		 * to set passenger's seat number
		 * @param seat take seat numbers value
		 */
		public void setSeat(String seat) {
			this.seat = seat;
		}
		
		/**
		 * to get passenger's group name (if go with group)
		 * @return group name
		 */
		public String getGroupName() {
			return groupName;
		}
		
		/**
		 * to set group name (if go with group)
		 * @param groupName gets group name value
		 */
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		
		/** 
		 * to recognize passenger going individual or group
		 * @return return "Individual" or "Group'
		 */
		public String getGroupType() {
			return groupType;
		}
		
		/**
		 * to set if passenger going alone or with group
		 * @param groupType gets value Individual of Group
		 */
		public void setGroupType(String groupType) {
			this.groupType = groupName;
		}
		
		
		
}
