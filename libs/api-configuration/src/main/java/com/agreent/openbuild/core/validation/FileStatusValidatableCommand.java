package com.agreent.openbuild.core.validation;

import java.util.Set;
import java.util.UUID;

public interface FileStatusValidatableCommand extends ValidatableCommand {

  Set<UUID> getFileIDs();
}
