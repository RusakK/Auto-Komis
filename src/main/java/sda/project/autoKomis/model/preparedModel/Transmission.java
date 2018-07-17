package sda.project.autoKomis.model.preparedModel;

public enum Transmission {


    MANUAL, AUTOMAT, SEMI_ATOMAT;

    public static Transmission getTransmission(Integer tranmissionId) {
        switch (tranmissionId) {
            case 1:
                return Transmission.MANUAL;
            case 2:
                return Transmission.AUTOMAT;
            case 3:
                return Transmission.SEMI_ATOMAT;
        }
        return null;
    }

}
