package in.accountantconnect.service.storage;

import in.accountantconnect.api.response.FileStorageResponse;
import in.accountantconnect.util.EnvManager;

public class FileStorageService {
	public FileStorageResponse getImage(String imageName){
		FileStorageResponse response = new FileStorageResponse();
		String imgStorageLocation = EnvManager.getUploadedImageStorageLocation();
		
		
		return response;
	}
}
