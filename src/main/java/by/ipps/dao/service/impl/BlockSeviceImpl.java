package by.ipps.dao.service.impl;

import by.ipps.dao.entity.Block;
import by.ipps.dao.repository.BlockRepository;
import by.ipps.dao.service.BlockService;
import by.ipps.dao.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BlockSeviceImpl extends BaseEntityServiceImpl<Block, BlockRepository>
    implements BlockService {
  public BlockSeviceImpl(BlockRepository repository) {
    super(repository);
  }
}
