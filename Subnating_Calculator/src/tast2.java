
class IPAddress {
    private String ipAddress;
    private int[] octets = new int[4];
    private String classType;

    public IPAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        parseIPAddress();
        determineClassType();
    }

    // Method to parse and validate the IP address
    private void parseIPAddress() {
        String[] octetStrings = ipAddress.split("\\.");
        if (octetStrings.length != 4) {
            throw new IllegalArgumentException("Invalid IP address format. Must be in the form of x.x.x.x");
        }
        for (int i = 0; i < 4; i++) {
            octets[i] = Integer.parseInt(octetStrings[i]);
            if (octets[i] < 0 || octets[i] > 255) {
                throw new IllegalArgumentException("Invalid octet value. Octets must be between 0 and 255.");
            }
        }
    }

    // Method to determine the class of the IP address based on the first octet
    private void determineClassType() {
        int firstOctet = octets[0];
        if (firstOctet >= 1 && firstOctet <= 126) {
            classType = "A";
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            classType = "B";
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            classType = "C";
        } else {
            throw new IllegalArgumentException("Invalid class type for the IP address.");
        }
    }

    public String getIPAddress() {
        return ipAddress;
    }

    public String getClassType() {
        return classType;
    }
}

class SubnetCalculator {
    private int neededHosts;
    private int prefixLength;

    public SubnetCalculator(int neededHosts) {
        this.neededHosts = neededHosts + 2;  // Adding 2 for network and broadcast
    }



    // Method to calculate subnet prefix length
    public int calculateSubnetPrefixLength() {
        int power = (int) Math.ceil(Math.log(neededHosts) / Math.log(2));
        prefixLength = 32 - power;
        return prefixLength;
    }

    // Method to calculate the subnet mask in binary based on the prefix length
    public String calculateSubnetMaskBinary() {
        StringBuilder binaryMask = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            binaryMask.append(i < prefixLength ? '1' : '0');
            if ((i + 1) % 8 == 0 && i < 31) {
                binaryMask.append('.');
            }
        }
        return binaryMask.toString();
    }

    // Method to calculate the subnet mask in decimal
    public String calculateSubnetMaskDecimal() {
        StringBuilder decimalMask = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String binaryOctet = calculateSubnetMaskBinary().split("\\.")[i];
            int decimal = Integer.parseInt(binaryOctet, 2);
            decimalMask.append(decimal);
            if (i < 3) {
                decimalMask.append('.');
            }
        }
        return decimalMask.toString();
    }
}