package com.cisco.ipphone.sdk;

public class SpeedDial {
  private String label;
  private String dirn;
  private int position;

  public SpeedDial(String label, String dirn, int position) {
    this.label = label;
    this.dirn = dirn;
    this.position = position;
  }

  public String getLabel() {
    return label;
  }

  public String getNumber() {
    return dirn;
  }

  public int getPosition() {
    return position;
  }
}
