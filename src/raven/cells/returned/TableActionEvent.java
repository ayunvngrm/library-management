/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package raven.cells.returned;

import raven.cells.*;

/**
 *
 * @author ayu novianingrum
 */
public interface TableActionEvent {
    public void onReturn(int row);
    public void onRemind(int row);
}
