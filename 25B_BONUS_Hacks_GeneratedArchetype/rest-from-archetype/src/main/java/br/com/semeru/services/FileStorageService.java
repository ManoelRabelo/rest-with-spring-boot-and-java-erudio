package br.com.semeru.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.semeru.config.FileStorageConfig;
import br.com.semeru.exceptions.FileStorageException;
import br.com.semeru.exceptions.MyFileNotFoundException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		Path path = Paths.get(fileStorageConfig.getUploadDir())
			.toAbsolutePath().normalize();
		
		this.fileStorageLocation = path;
		
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException
				("Cold not create the directory where the uploaded files will be stored!", e);
		}
	}
	
	public String storeFile(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			// Filename..txt
			if (filename.contains("..")) {
				throw new FileStorageException
					("Sory! Filename contains invalid path sequence " + filename);
			}
			
			Path targetLocation = this.fileStorageLocation.resolve(filename);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			return filename;
		} catch (Exception e) {
			throw new FileStorageException
				("Cold not store file " + filename + ". Please try again!", e);
		}
	}
	
	public Resource loadFileAsResource (String filename) {
		
		try {
			Path filepath = this.fileStorageLocation.resolve(filename).normalize();
			Resource resource = new UrlResource(filepath.toUri());
			
			if(resource.exists()) 
				return resource;
			else 
				throw new MyFileNotFoundException("File not found");
			
		} catch (Exception e) {
			throw new MyFileNotFoundException("File not found", e);
		}
		
		
		
		
		
	}
}
