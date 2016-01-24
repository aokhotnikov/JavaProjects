package examples;

import org.pcap4j.core.PcapNetworkInterface;
import pcap.Convert;
import pcap.Pcap;
import pcap.Threads;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by FBI on 24.01.2016.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String iface = Pcap.interfaces().get(0).getName();

        sendPacket(iface);
        getPackage(iface);
    }

    private static void getPackage(String iface) throws IOException{

        System.out.println("Listening...");

        String sourceMac = Convert.bytes2hex(Pcap.get(iface).getLinkLayerAddresses().get(0).getAddress());
        String sourceIp = "192.168.1.2";
        System.out.println("--> " + sourceMac + " -- " + sourceIp);

        Closeable c  = Pcap.listen(iface, new Pcap.Listener() {
            public void onPacket(byte[] bytes) {

                String response = Convert.bytes2hex(bytes);
                if (response.substring(36,41).equals("08 06")){
                    String targetMac = response.substring(0,17);
                    String targetIp = Integer.parseInt(response.substring(114,116), 16) + "." +
                                      Integer.parseInt(response.substring(117,119), 16) + "." +
                                      Integer.parseInt(response.substring(120,122), 16) + "." +
                                      Integer.parseInt(response.substring(123,125), 16);
                    System.out.println("    " + targetMac + " -- " + targetIp);
                }
            }
        });
        //System.err.println("Press Enter to close");
        System.in.read(); // blocks here until user presses Enter

        c.close();
    }

    public static void sendPacket(String iface){
        String sourceMac = Convert.bytes2hex(Pcap.get(iface).getLinkLayerAddresses().get(0).getAddress());
        String sourceIp = Convert.dec2hex("192.168.1.2");
        String targetMac = "ff:ff:ff ff:ff:ff";
        String targetIp = Convert.dec2hex("1.2.3.4");

        byte[] packet = Convert.hex2bytes( // ----- Ethernet
                targetMac,                 // Destination: ff:ff:ff:ff:ff:ff
                sourceMac,                 // Source: __:__:__:__:__:__
                "08 06",                   // Type: ARP (0x0806)
                // ----- ARP
                "00 01",                   // Hardware type: Ethernet (1)
                "08 00",                   // Protocol type: IPv4 (0x0800)
                "06",                      // Hardware size: 6
                "04",                      // Protocol size: 4
                "00 01",                   // Opcode: request (1)
                sourceMac,                 // Sender MAC address: 6 bytes
                sourceIp,                  // Sender IP address:  4 bytes
                targetMac,                 // Target MAC address: 6 bytes
                targetIp                   // Target IP address:  4 bytes
        );

        System.out.println("Sending [" + Convert.bytes2hex(packet) + "]...");
        Pcap.send(iface, packet);
    }


}
