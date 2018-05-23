package com.att.eg.profile.mysubscriptions.info.common;

import com.att.ajsc.common.discovery.model.*;
import com.att.ajsc.common.discovery.model.enums.ConsumableType;
import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by mguttman on 12/09/2017.
 */
public class Utils {
    private static final YawlLogger yawl = new YawlLogger(Utils.class);
    private static final Logger fLogger = LoggerFactory.getLogger(Utils.class.getPackage().getName());
    private Utils() {
        // a utility class with static method
        // that should not be instantiated
    }

    public static String getConsumableDetails(Consumable consumable) {
        StringBuilder sb = new StringBuilder();
        sb.append("resourceID: ");
        sb.append(consumable.getResourceId());
        Date startTime;
        Date endTime;

        sb.append("; ");
        sb.append("start time: ");
        startTime = consumable.getStartTime();
        endTime = consumable.getEndTime();
        if (startTime != null) {
            sb.append(new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(startTime));
        } else {
            sb.append("missing");
        }
        sb.append("; ");
        sb.append("end time: ");
        if (endTime != null) {
            sb.append(new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(endTime));
        } else {
            sb.append("missing");
        }
        return sb.toString();
    }

    public static boolean isConsumableLinear(Consumable consumable) {
        boolean retVal = consumable.getConsumableType().equals(ConsumableType.LINEAR);
        yawl.debug(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                .setKeyAndValue(Constants.CONTENT_ID, consumable.getResourceId())
                .setKeyAndValue("isLinear", Boolean.toString(retVal))
                .create());
        return retVal;
    }

    public static boolean isInFuture(Consumable consumable, Date currentDate) {
        Date startDate = consumable.getStartTime();
        if (startDate == null) {
            return false;
        }
        boolean retVal = false;
        if ( startDate.compareTo(currentDate) > 0){
            retVal = true;
        }
        printConsumableResultsToLog(consumable, startDate, currentDate, retVal);
        return retVal;
    }

    public static boolean isConsumableCurrentlyPlaying(Consumable consumable, Date currentDate) {
        Date startDate = consumable.getStartTime();
        Date endDate = consumable.getEndTime();
        if (startDate == null || endDate == null) {
            return false;
        }
        boolean retVal = false;

        if ( startDate.compareTo(currentDate) < 0 && endDate.compareTo(currentDate) > 0 ){
            retVal = true;
        }
        if (fLogger.isDebugEnabled()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_STR);
            yawl.debug(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                    .setKeyAndValue("isConsumableCurrentlyPlaying", Boolean.toString(retVal))
                    .setKeyAndValue(Constants.RESOURCE_ID, consumable.getResourceId())
                    .setKeyAndValue("CurrentTime", dateFormat.format(currentDate))
                    .setKeyAndValue(Constants.START_TIME_STRING, dateFormat.format(startDate))
                    .setKeyAndValue("EndTime", dateFormat.format(endDate))
                    .create());
        }
        return retVal;
    }

    public static boolean isConsumableInFutureOrCurrentlyPlaying(Consumable consumable, Date currentDate) {
        return isInFuture(consumable, currentDate) || isConsumableCurrentlyPlaying(consumable, currentDate);
    }

    public static boolean isRecordable(Consumable consumable) {
        Boolean retVal = false;
        Augmentation augmentation = consumable.getAugmentation();
        if (augmentation != null) {
            Constraints constraints = augmentation.getConstraints();
            if (constraints != null) {
                retVal = constraints.getIsRecordable();
            }
        }
        yawl.debug(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                .setKeyAndValue(Constants.CONTENT_ID, consumable.getResourceId())
                .setKeyAndValue(Constants.IS_RECORDABLE, Boolean.toString(retVal))
                .create());
        return (retVal != null) && retVal.booleanValue();
    }

    public static boolean isSubscribed(Consumable consumable) {
        Channel channel = consumable.getChannel();
        boolean retVal = (channel != null) && isChannelSubscribed(channel);
        yawl.debug(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                .setKeyAndValue(Constants.CONTENT_ID, consumable.getResourceId())
                .setKeyAndValue("Subscribed", Boolean.toString(retVal))
                .create());
        return retVal;
    }

    private static void printConsumableResultsToLog(Consumable consumable,
                                                            Date startDate,
                                                            Date currentDate,
                                                            boolean isInFuture) {
        if (fLogger.isDebugEnabled()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_STR);
            yawl.debug(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                    .setNote("consumable start time in future")
                    .setKeyAndValue("isInFuture", String.valueOf(isInFuture))
                    .setKeyAndValue(Constants.RESOURCE_ID, consumable.getResourceId())
                    .setKeyAndValue("CurrentTime", dateFormat.format(currentDate))
                    .setKeyAndValue(Constants.START_TIME_STRING, dateFormat.format(startDate))
                    .create());
        }
    }

    private static boolean isChannelSubscribed(Channel channel) {
        Boolean retVal = false;

        Augmentation augmentation = channel.getAugmentation();
        if (augmentation != null) {
            Constraints constraints = augmentation.getConstraints();
            if (constraints != null) {
                retVal = constraints.getIsSubscribed();
            }
        }
        return (retVal != null) && retVal.booleanValue();
    }

    public static boolean isFirstRunContent(Content content, long maxTimeFromOriginalAirDate) {
        if(content.getOriginalAirDate() == null) {
            yawl.info(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                    .setNote("no original air date in content, consider first run")
                    .setKeyAndValue(Constants.RESOURCE_ID, content.getResourceId())
                    .create());
            return true;
        }
        Consumable consumable = content.getConsumables().get(0);

        boolean isFirstRun = false;
        long gapFromOriginalAirDate = consumable.getStartTime().getTime() - content.getOriginalAirDate().getTime();
        if(gapFromOriginalAirDate <= maxTimeFromOriginalAirDate){
            isFirstRun = true;
        }
        if (fLogger.isDebugEnabled()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_STR);
            yawl.debug(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                    .setKeyAndValue("isFirstRun", String.valueOf(isFirstRun))
                    .setKeyAndValue(Constants.RESOURCE_ID, consumable.getResourceId())
                    .setKeyAndValue(Constants.START_TIME_STRING, dateFormat.format(consumable.getStartTime()))
                    .setKeyAndValue("OriginalAirDate", dateFormat.format(content.getOriginalAirDate()))
                    .setKeyAndValue("gapInMS", String.valueOf(gapFromOriginalAirDate))
                    .setKeyAndValue("maxTimeFromOriginalAirDate", String.valueOf(maxTimeFromOriginalAirDate))
                    .create());
        }
        return isFirstRun;
    }


    public static boolean isConsumableExcludedFromChannels(Consumable consumable, List<String> channels) {
        boolean retVal = true;
        Channel channel = consumable.getChannel();
        if (channels.contains(channel.getCcId())) {
            yawl.debug(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                    .setNote("included in channels")
                    .setKeyAndValue(Constants.RESOURCE_ID, consumable.getResourceId())
                    .setKeyAndValue(Constants.CC_ID, channel.getCcId())
                    .create());
            retVal = false;
        }
        return retVal;
    }

    public static boolean hasValidConsumable(Content content) {
        List<Consumable> consumableList = content.getConsumables();
        if (consumableList != null && !consumableList.isEmpty() && consumableList.get(0) != null ) {
            return true;
        }
        yawl.warn(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                .setNote("consumable not found in content")
                .setKeyAndValue(Constants.CONTENT_ID, content.getResourceId())
                .create());
        return false;
    }


    public static String extractValuesFromClientContextKey(String clientContext, String variable) {

        ArrayList<String> dmaIds = new ArrayList<>();
        dmaIds.add("");

        String matchingKey = variable.toUpperCase(Locale.getDefault());
        if (StringUtils.isNotBlank(clientContext)) {
            List<String> clientContextPairs = Arrays.asList(clientContext.split(","));
            clientContextPairs.stream()
                .filter(clientContextPair -> clientContextPair.trim().toUpperCase(Locale.getDefault()).startsWith(matchingKey))
                .forEach(clientContextPair -> {
                    String[] clientContextKeyValue = clientContextPair.split(":");
                    if ((clientContextKeyValue.length == 2) &&
                            clientContextKeyValue[0].trim().equalsIgnoreCase(matchingKey)) {
                            dmaIds.set(0, clientContextKeyValue[1].trim());
                    }
                });
        }

        return dmaIds.get(0);
    }

    public static long hoursToMS(long hours) {
        return hours * 3600000;
    }

    public static long calculateEndTime(long startTime,
                                        long timePeriodInMS,
                                        long finalEndTime) {
        long endTime = startTime + timePeriodInMS;
        if (endTime > finalEndTime) {
            endTime = finalEndTime;
        }
        return endTime;
    }


}

