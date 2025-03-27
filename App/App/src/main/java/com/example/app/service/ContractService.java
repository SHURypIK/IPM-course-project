package com.example.app.service;

import com.example.app.AppState;
import com.example.app.model.LeaseContract;
import com.example.app.model.Resident;
import com.example.app.repositories.ContractRepo;
import com.example.app.repositories.MoveRepo;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ContractService {

    public static LeaseContract fill(DatePicker date, DatePicker dateUntil, TextField lessee){
        LeaseContract leaseContract = AppState.getInstance().getResident().getLeaseContract();
        if (leaseContract != null) {
            date.setValue(leaseContract.getContractDate());
            dateUntil.setValue(leaseContract.getValidUntil());
            lessee.setText(leaseContract.getLessee());
            return leaseContract;
        }
        return null;
    }

    public static LeaseContract delete(LeaseContract contract) throws Exception {
        ContractRepo.delete(contract.getId());
        updateResident();
        return null;

    }

    public static LeaseContract save(LeaseContract contract) throws Exception {
        LeaseContract contract1 = ContractRepo.add(contract);
        updateResident();
        return contract1;
    }

    public static LeaseContract update(LeaseContract contract) throws Exception {
        LeaseContract contract1 = ContractRepo.update(contract, contract.getId());
        updateResident();
        return contract1;
    }

    private static void updateResident() throws Exception {
        Resident resident = MoveRepo.getResident(AppState.getInstance().getResidentShort().getFio());
        AppState.getInstance().setResident(resident);
    }

}
