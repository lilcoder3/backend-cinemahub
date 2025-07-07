package pe.edu.upc.trabajopelicula.serviceinterfaces;

import pe.edu.upc.trabajopelicula.entities.TypePayments;

import java.util.List;

public interface ITypePaymentsInterface {
    public void insert(TypePayments typePayments);
    public List<TypePayments> list();
    public void delete(int id);
    public void update(TypePayments typePayments);
    public TypePayments listarId(int id);
}
