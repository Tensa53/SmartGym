package com.example.smartgym;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.concurrent.Executor;

public class LoginTask extends Task<AuthResult> {

    boolean completed;

    boolean canceled;

    boolean successful;

    public LoginTask() {
    }

    public LoginTask(boolean successful) {
        this.successful = successful;
    }

    public LoginTask(boolean completed, boolean canceled, boolean successful) {
        this.completed = completed;
        this.canceled = canceled;
        this.successful = successful;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnSuccessListener(@NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
        return null;
    }

    @NonNull
    @Override
    public Task<AuthResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super AuthResult> onSuccessListener) {
        return null;
    }

    @Nullable
    @Override
    public Exception getException() {
        return null;
    }

    @Override
    public AuthResult getResult() {
        return null;
    }

    @Override
    public <X extends Throwable> AuthResult getResult(@NonNull Class<X> aClass) throws X {
        return null;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public boolean isComplete() {
        return completed;
    }

    @Override
    public boolean isSuccessful() {
        return successful;
    }
}
