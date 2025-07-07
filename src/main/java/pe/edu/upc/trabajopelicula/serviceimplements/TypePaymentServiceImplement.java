package pe.edu.upc.trabajopelicula.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.trabajopelicula.entities.TypePayments;
import pe.edu.upc.trabajopelicula.repositories.ICinemaRepository;
import pe.edu.upc.trabajopelicula.repositories.ITypePaymentsRepository;
import pe.edu.upc.trabajopelicula.serviceinterfaces.ITypePaymentsInterface;

import java.util.List;

@Service
public class TypePaymentServiceImplement implements ITypePaymentsInterface {
    @Autowired
    private ITypePaymentsRepository tyR;

    @Override
    public void insert(TypePayments typePayments) {
        tyR.save(typePayments);
    }

    @Override
    public List<TypePayments> list() {
        return tyR.findAll();
    }

    @Override
    public void delete(int id) {
        tyR.deleteById(id);
    }

    @Override
    public void update(TypePayments typePayments) {
        tyR.save(typePayments);
    }

    @Override
    public TypePayments listarId(int id) {
        return tyR.findById(id).orElse(new TypePayments());
    }
}
