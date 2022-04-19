package term2048.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import term2048.ui.TileInfoRepository;
import term2048.ui.constants.TileInfo;

public class InfoLoader {

    private static final String PATH = "config.yaml";

    public static void load() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(TileInfo.class, new InfoParser());
        mapper.registerModule(module);
        try {
            URL url = InfoLoader.class.getClassLoader().getResource(PATH);
            if (url == null) {
                throw new IllegalArgumentException("can not find native library");
            }
            File file = new File(url.getFile());
            System.out.println(url.getFile());
            TileInfoRepository.addInfos(
                mapper.readValue(file, new TypeReference<>() {}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
