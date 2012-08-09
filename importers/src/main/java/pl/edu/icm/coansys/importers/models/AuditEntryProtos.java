/*
 * (C) 2010-2012 ICM UW. All rights reserved.
 */

package pl.edu.icm.coansys.importers.models;

public final class AuditEntryProtos {
  private AuditEntryProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public enum Level
      implements com.google.protobuf.ProtocolMessageEnum {
    FATAL(0, 0),
    ERROR(1, 1),
    WARN(2, 2),
    INFO(3, 3),
    DEBUG(4, 4),
    TRACE(5, 5),
    ;
    
    public static final int FATAL_VALUE = 0;
    public static final int ERROR_VALUE = 1;
    public static final int WARN_VALUE = 2;
    public static final int INFO_VALUE = 3;
    public static final int DEBUG_VALUE = 4;
    public static final int TRACE_VALUE = 5;
    
    
    public final int getNumber() { return value; }
    
    public static Level valueOf(int value) {
      switch (value) {
        case 0: return FATAL;
        case 1: return ERROR;
        case 2: return WARN;
        case 3: return INFO;
        case 4: return DEBUG;
        case 5: return TRACE;
        default: return null;
      }
    }
    
    public static com.google.protobuf.Internal.EnumLiteMap<Level>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<Level>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Level>() {
            public Level findValueByNumber(int number) {
              return Level.valueOf(number);
            }
          };
    
    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return pl.edu.icm.coansys.importers.models.AuditEntryProtos.getDescriptor().getEnumTypes().get(0);
    }
    
    private static final Level[] VALUES = {
      FATAL, ERROR, WARN, INFO, DEBUG, TRACE, 
    };
    
    public static Level valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }
    
    private final int index;
    private final int value;
    
    private Level(int index, int value) {
      this.index = index;
      this.value = value;
    }
    
    // @@protoc_insertion_point(enum_scope:pl.edu.icm.coansys.logs.Level)
  }
  
  public interface EntryOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required string event_id = 1;
    boolean hasEventId();
    String getEventId();
    
    // required .pl.edu.icm.coansys.logs.Level level = 2;
    boolean hasLevel();
    pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level getLevel();
    
    // required string service_id = 3;
    boolean hasServiceId();
    String getServiceId();
    
    // required string event_type = 4;
    boolean hasEventType();
    String getEventType();
    
    // required int64 timestamp = 5;
    boolean hasTimestamp();
    long getTimestamp();
    
    // repeated string arg = 6;
    java.util.List<String> getArgList();
    int getArgCount();
    String getArg(int index);
  }
  public static final class Entry extends
      com.google.protobuf.GeneratedMessage
      implements EntryOrBuilder {
    // Use Entry.newBuilder() to construct.
    private Entry(Builder builder) {
      super(builder);
    }
    private Entry(boolean noInit) {}
    
    private static final Entry defaultInstance;
    public static Entry getDefaultInstance() {
      return defaultInstance;
    }
    
    public Entry getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pl.edu.icm.coansys.importers.models.AuditEntryProtos.internal_static_pl_edu_icm_coansys_logs_Entry_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pl.edu.icm.coansys.importers.models.AuditEntryProtos.internal_static_pl_edu_icm_coansys_logs_Entry_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required string event_id = 1;
    public static final int EVENT_ID_FIELD_NUMBER = 1;
    private java.lang.Object eventId_;
    public boolean hasEventId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public String getEventId() {
      java.lang.Object ref = eventId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          eventId_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getEventIdBytes() {
      java.lang.Object ref = eventId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        eventId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // required .pl.edu.icm.coansys.logs.Level level = 2;
    public static final int LEVEL_FIELD_NUMBER = 2;
    private pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level level_;
    public boolean hasLevel() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level getLevel() {
      return level_;
    }
    
    // required string service_id = 3;
    public static final int SERVICE_ID_FIELD_NUMBER = 3;
    private java.lang.Object serviceId_;
    public boolean hasServiceId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public String getServiceId() {
      java.lang.Object ref = serviceId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          serviceId_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getServiceIdBytes() {
      java.lang.Object ref = serviceId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        serviceId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // required string event_type = 4;
    public static final int EVENT_TYPE_FIELD_NUMBER = 4;
    private java.lang.Object eventType_;
    public boolean hasEventType() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public String getEventType() {
      java.lang.Object ref = eventType_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          eventType_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getEventTypeBytes() {
      java.lang.Object ref = eventType_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        eventType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // required int64 timestamp = 5;
    public static final int TIMESTAMP_FIELD_NUMBER = 5;
    private long timestamp_;
    public boolean hasTimestamp() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    public long getTimestamp() {
      return timestamp_;
    }
    
    // repeated string arg = 6;
    public static final int ARG_FIELD_NUMBER = 6;
    private com.google.protobuf.LazyStringList arg_;
    public java.util.List<String>
        getArgList() {
      return arg_;
    }
    public int getArgCount() {
      return arg_.size();
    }
    public String getArg(int index) {
      return arg_.get(index);
    }
    
    private void initFields() {
      eventId_ = "";
      level_ = pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level.FATAL;
      serviceId_ = "";
      eventType_ = "";
      timestamp_ = 0L;
      arg_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasEventId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasLevel()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasServiceId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasEventType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasTimestamp()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getEventIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeEnum(2, level_.getNumber());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getServiceIdBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(4, getEventTypeBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt64(5, timestamp_);
      }
      for (int i = 0; i < arg_.size(); i++) {
        output.writeBytes(6, arg_.getByteString(i));
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(1, getEventIdBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, level_.getNumber());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getServiceIdBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, getEventTypeBytes());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(5, timestamp_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < arg_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(arg_.getByteString(i));
        }
        size += dataSize;
        size += 1 * getArgList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements pl.edu.icm.coansys.importers.models.AuditEntryProtos.EntryOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return pl.edu.icm.coansys.importers.models.AuditEntryProtos.internal_static_pl_edu_icm_coansys_logs_Entry_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return pl.edu.icm.coansys.importers.models.AuditEntryProtos.internal_static_pl_edu_icm_coansys_logs_Entry_fieldAccessorTable;
      }
      
      // Construct using pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        eventId_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        level_ = pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level.FATAL;
        bitField0_ = (bitField0_ & ~0x00000002);
        serviceId_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        eventType_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        timestamp_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000010);
        arg_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000020);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry.getDescriptor();
      }
      
      public pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry getDefaultInstanceForType() {
        return pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry.getDefaultInstance();
      }
      
      public pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry build() {
        pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry buildPartial() {
        pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry result = new pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.eventId_ = eventId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.level_ = level_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.serviceId_ = serviceId_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.eventType_ = eventType_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.timestamp_ = timestamp_;
        if (((bitField0_ & 0x00000020) == 0x00000020)) {
          arg_ = new com.google.protobuf.UnmodifiableLazyStringList(
              arg_);
          bitField0_ = (bitField0_ & ~0x00000020);
        }
        result.arg_ = arg_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry) {
          return mergeFrom((pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry other) {
        if (other == pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry.getDefaultInstance()) return this;
        if (other.hasEventId()) {
          setEventId(other.getEventId());
        }
        if (other.hasLevel()) {
          setLevel(other.getLevel());
        }
        if (other.hasServiceId()) {
          setServiceId(other.getServiceId());
        }
        if (other.hasEventType()) {
          setEventType(other.getEventType());
        }
        if (other.hasTimestamp()) {
          setTimestamp(other.getTimestamp());
        }
        if (!other.arg_.isEmpty()) {
          if (arg_.isEmpty()) {
            arg_ = other.arg_;
            bitField0_ = (bitField0_ & ~0x00000020);
          } else {
            ensureArgIsMutable();
            arg_.addAll(other.arg_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasEventId()) {
          
          return false;
        }
        if (!hasLevel()) {
          
          return false;
        }
        if (!hasServiceId()) {
          
          return false;
        }
        if (!hasEventType()) {
          
          return false;
        }
        if (!hasTimestamp()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              eventId_ = input.readBytes();
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
              pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level value = pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                bitField0_ |= 0x00000002;
                level_ = value;
              }
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              serviceId_ = input.readBytes();
              break;
            }
            case 34: {
              bitField0_ |= 0x00000008;
              eventType_ = input.readBytes();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              timestamp_ = input.readInt64();
              break;
            }
            case 50: {
              ensureArgIsMutable();
              arg_.add(input.readBytes());
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required string event_id = 1;
      private java.lang.Object eventId_ = "";
      public boolean hasEventId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public String getEventId() {
        java.lang.Object ref = eventId_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          eventId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setEventId(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        eventId_ = value;
        onChanged();
        return this;
      }
      public Builder clearEventId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        eventId_ = getDefaultInstance().getEventId();
        onChanged();
        return this;
      }
      void setEventId(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000001;
        eventId_ = value;
        onChanged();
      }
      
      // required .pl.edu.icm.coansys.logs.Level level = 2;
      private pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level level_ = pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level.FATAL;
      public boolean hasLevel() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level getLevel() {
        return level_;
      }
      public Builder setLevel(pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        level_ = value;
        onChanged();
        return this;
      }
      public Builder clearLevel() {
        bitField0_ = (bitField0_ & ~0x00000002);
        level_ = pl.edu.icm.coansys.importers.models.AuditEntryProtos.Level.FATAL;
        onChanged();
        return this;
      }
      
      // required string service_id = 3;
      private java.lang.Object serviceId_ = "";
      public boolean hasServiceId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public String getServiceId() {
        java.lang.Object ref = serviceId_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          serviceId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setServiceId(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        serviceId_ = value;
        onChanged();
        return this;
      }
      public Builder clearServiceId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        serviceId_ = getDefaultInstance().getServiceId();
        onChanged();
        return this;
      }
      void setServiceId(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000004;
        serviceId_ = value;
        onChanged();
      }
      
      // required string event_type = 4;
      private java.lang.Object eventType_ = "";
      public boolean hasEventType() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public String getEventType() {
        java.lang.Object ref = eventType_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          eventType_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setEventType(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        eventType_ = value;
        onChanged();
        return this;
      }
      public Builder clearEventType() {
        bitField0_ = (bitField0_ & ~0x00000008);
        eventType_ = getDefaultInstance().getEventType();
        onChanged();
        return this;
      }
      void setEventType(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000008;
        eventType_ = value;
        onChanged();
      }
      
      // required int64 timestamp = 5;
      private long timestamp_ ;
      public boolean hasTimestamp() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      public long getTimestamp() {
        return timestamp_;
      }
      public Builder setTimestamp(long value) {
        bitField0_ |= 0x00000010;
        timestamp_ = value;
        onChanged();
        return this;
      }
      public Builder clearTimestamp() {
        bitField0_ = (bitField0_ & ~0x00000010);
        timestamp_ = 0L;
        onChanged();
        return this;
      }
      
      // repeated string arg = 6;
      private com.google.protobuf.LazyStringList arg_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureArgIsMutable() {
        if (!((bitField0_ & 0x00000020) == 0x00000020)) {
          arg_ = new com.google.protobuf.LazyStringArrayList(arg_);
          bitField0_ |= 0x00000020;
         }
      }
      public java.util.List<String>
          getArgList() {
        return java.util.Collections.unmodifiableList(arg_);
      }
      public int getArgCount() {
        return arg_.size();
      }
      public String getArg(int index) {
        return arg_.get(index);
      }
      public Builder setArg(
          int index, String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureArgIsMutable();
        arg_.set(index, value);
        onChanged();
        return this;
      }
      public Builder addArg(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureArgIsMutable();
        arg_.add(value);
        onChanged();
        return this;
      }
      public Builder addAllArg(
          java.lang.Iterable<String> values) {
        ensureArgIsMutable();
        super.addAll(values, arg_);
        onChanged();
        return this;
      }
      public Builder clearArg() {
        arg_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000020);
        onChanged();
        return this;
      }
      void addArg(com.google.protobuf.ByteString value) {
        ensureArgIsMutable();
        arg_.add(value);
        onChanged();
      }
      
      // @@protoc_insertion_point(builder_scope:pl.edu.icm.coansys.logs.Entry)
    }
    
    static {
      defaultInstance = new Entry(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:pl.edu.icm.coansys.logs.Entry)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_pl_edu_icm_coansys_logs_Entry_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_pl_edu_icm_coansys_logs_Entry_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020auditentry.proto\022\027pl.edu.icm.coansys.l" +
      "ogs\"\220\001\n\005Entry\022\020\n\010event_id\030\001 \002(\t\022-\n\005level" +
      "\030\002 \002(\0162\036.pl.edu.icm.coansys.logs.Level\022\022" +
      "\n\nservice_id\030\003 \002(\t\022\022\n\nevent_type\030\004 \002(\t\022\021" +
      "\n\ttimestamp\030\005 \002(\003\022\013\n\003arg\030\006 \003(\t*G\n\005Level\022" +
      "\t\n\005FATAL\020\000\022\t\n\005ERROR\020\001\022\010\n\004WARN\020\002\022\010\n\004INFO\020" +
      "\003\022\t\n\005DEBUG\020\004\022\t\n\005TRACE\020\005B7\n#pl.edu.icm.co" +
      "ansys.importers.modelsB\020AuditEntryProtos"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_pl_edu_icm_coansys_logs_Entry_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_pl_edu_icm_coansys_logs_Entry_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_pl_edu_icm_coansys_logs_Entry_descriptor,
              new java.lang.String[] { "EventId", "Level", "ServiceId", "EventType", "Timestamp", "Arg", },
              pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry.class,
              pl.edu.icm.coansys.importers.models.AuditEntryProtos.Entry.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
