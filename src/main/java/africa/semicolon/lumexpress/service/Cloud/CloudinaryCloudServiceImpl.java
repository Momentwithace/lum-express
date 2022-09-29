package africa.semicolon.lumexpress.service.Cloud;

import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
@Service
public class CloudinaryCloudServiceImpl implements CloudService{
    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dpe1lbum0",
            "api_key", "698695718922945",
            "api_secret", "G-nkU1AbyyDkLyj20xAUsPSCMKo",
            "secure", true
    ));

    @Override
    public String upload(byte[] imageBytes, Map<?, ?> map) throws IOException {
        var uploadResponse = cloudinary
                .uploader()
                .upload(imageBytes, ObjectUtils.emptyMap());
        return (String)uploadResponse.get("url");

    }
}
