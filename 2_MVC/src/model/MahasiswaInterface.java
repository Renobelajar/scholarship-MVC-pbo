package model;

import java.util.List;

public interface MahasiswaInterface {
    public void insert(Mahasiswa mahasiswa);
    public void update(Mahasiswa mahasiswa);
    public void delete(String nim);
    public List<Mahasiswa> getAll();
}