package ua.epam.spring.hometask.utils;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UploadParser {

	public static <T> T parseJson(MultipartFile file, Class<?> theClass) throws IOException, ClassNotFoundException {
			ObjectMapper mapper = new ObjectMapper();
			byte[] bytes = file.getBytes();
			String jsonString = new String(bytes, "UTF-8");
			mapper.findAndRegisterModules();
			return mapper.readValue(jsonString,
					mapper.getTypeFactory().constructCollectionType(List.class, Class.forName(theClass.getName())));
	}
}
