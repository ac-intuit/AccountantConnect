package in.accountantconnect.util;

public class EnumCollection {
	
	public enum Environment {
		Prod, Dev
	}
	
	public enum BooleanEnum {
		Y, N
	}
	
	public enum EventStatus {
		failure(0),success(1);		
		private Integer value;
		
		private EventStatus(Integer value) {
			this.value = value;
		}		
		public Integer getValue() {
			return value;
		}
	}
}