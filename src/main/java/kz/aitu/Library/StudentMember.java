package kz.aitu.Library;

import kz.aitu.Library.entities.LibraryMember;

public class StudentMember extends LibraryMember {
    public StudentMember(int id, String name) {
        super(id, name);
    }

    @Override
    public int getMaxBorrowCount() {
        return 3;
    }
}
