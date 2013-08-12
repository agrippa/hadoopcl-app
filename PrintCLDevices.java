import com.amd.aparapi.device.Device;
import com.amd.aparapi.internal.util.OpenCLUtil;
import com.amd.aparapi.internal.opencl.OpenCLPlatform;
import com.amd.aparapi.device.OpenCLDevice;
import com.amd.aparapi.device.Device.TYPE;
import java.util.List;

public class PrintCLDevices {
    public static String deviceTypeString(Device.TYPE type) {
        switch(type) {
            case UNKNOWN:
                return "UNKNOWN";
            case GPU:
                return "GPU";
            case CPU:
                return "CPU";
            case JTP:
                return "JTP";
            case SEQ:
                return "SEQ";
            case JAVA:
                return "JAVA";
        }
        return "UNKNOWN";
    }

    public static void main(String[] args) {
      List<OpenCLPlatform> platforms = OpenCLUtil.getOpenCLPlatforms();
      int countDev = 0;
      for(OpenCLPlatform platform : platforms) {
          for(OpenCLDevice device : platform.getOpenCLDevices()) {
              System.out.println("Device "+countDev+" is "+deviceTypeString(device.getType()));
              countDev ++;
          }
      }

    }
}

