package ru.itmo.kotikiservices.models;

public enum Color {
    BLACK,
    WHITE,
    GRAY,
    CALICO;

    public Color getColorById(int id) throws Exception {
        for (Color color: Color.values()) {
            if (color.ordinal() == id) {
                return color;
            }
        }
        throw new Exception("No color with this id");
    }
}
